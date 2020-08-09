import React, { useState, useEffect, useMemo } from 'react';
import qs from 'qs';
import { Form, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';

import History from '../History/History';
import './Converter.css';
import getValuteList, { valuteToString } from '../utils/getValuteList';
import { API_URL } from '../constants';

const useGetState = () => {
  const [valutes, setValutes] = useState([]);
  const [from, setFrom] = useState('');
  const [to, setTo] = useState('');
  const [summ, setSumm] = useState(0);
  const [convertedSumm, setConvertedSumm] = useState();

  useEffect(() => {
    (async () => {
      const responseRaw = await fetch(`${API_URL}/valute/all`);
      const response = await responseRaw.json();
      setValutes(response);
      const [defaultItem] = response || [];

      if (defaultItem) {
        const defaultItemOption = valuteToString(
          defaultItem.charCode,
          defaultItem.name
        );
        setFrom(defaultItemOption);
        setTo(defaultItemOption);
      }
    })();
  }, []);

  return {
    valutes,
    from,
    setFrom,
    to,
    setTo,
    summ,
    setSumm,
    convertedSumm,
    setConvertedSumm,
  };
};

const Converter = () => {
  const {
    valutes,
    from,
    setFrom,
    to,
    setTo,
    setSumm,
    convertedSumm,
    setConvertedSumm,
    summ,
  } = useGetState();

  const valutesList = useMemo(() => getValuteList({ valutes }), [valutes]);

  return (
    <div className='main-wrapper'>
      <div className='converter-wrapper'>
        <h3>Конвертер</h3>
        <Form className='inputs-wrapper'>
          <Form.Group>
            <Form.Control
              onChange={(e) => setFrom(e.target.value)}
              as='select'
              size='sm'
              custom
            >
              {valutesList}
            </Form.Control>
            <Form.Control onChange={(e) => setSumm(e.target.value)} />
          </Form.Group>
          <Form.Group>
            <Form.Control
              onChange={(e) => setTo(e.target.value)}
              as='select'
              size='sm'
              custom
            >
              {valutesList}
            </Form.Control>
            <Form.Control value={convertedSumm} />
          </Form.Group>
        </Form>

        <Button
          onClick={async () => {
            const [fromCharCode] = from.split('(');
            const [toCharCode] = to.split('(');
            const query = qs.stringify(
              {
                fromId: fromCharCode,
                toId: toCharCode,
                value: summ,
              },
              { addQueryPrefix: true }
            );
            const responseRaw = await fetch(
              `${API_URL}/valute/convert${query}`
            );
            const response = await responseRaw.json();
            setConvertedSumm(response);
          }}
          variant='primary'
        >
          Конвертировать
        </Button>
      </div>
      <History valutes={valutes} />
    </div>
  );
};

export default Converter;
