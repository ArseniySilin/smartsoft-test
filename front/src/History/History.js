import React, { useState } from 'react';
import qs from 'qs';
import { Table, Button, Form } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';

import getValutesList from '../utils/getValuteList';
import './History.css';
import { API_URL } from '../constants';

const useGetState = () => {
  const [history, setHistory] = useState([]);
  const [date, setDate] = useState(new Date());
  const [from, setFrom] = useState('RUB');
  const [to, setTo] = useState('RUB');

  return {
    history,
    setHistory,
    from,
    to,
    date,
    setDate,
    setFrom,
    setTo,
  };
};

const History = ({ valutes }) => {
  const {
    history,
    setHistory,
    date,
    setDate,
    from,
    setFrom,
    to,
    setTo,
  } = useGetState();

  return (
    <div className='history-wrapper'>
      <h3>История</h3>
      <Form.Group className='filters'>
        <DatePicker
          dateFormat='dd.MM.yyyy'
          selected={date}
          onChange={(date) => setDate(date)}
        />

        <Form.Control
          onChange={(e) => setFrom(e.target.value)}
          as='select'
          size='sm'
          custom
        >
          {getValutesList({ valutes, withNames: false })}
        </Form.Control>
        <Form.Control
          onChange={(e) => setTo(e.target.value)}
          as='select'
          size='sm'
          custom
        >
          {getValutesList({ valutes, withNames: false })}
        </Form.Control>
      </Form.Group>
      {Array.isArray(history) && history.length > 0 && (
        <Table striped bordered>
          <thead>
            <tr>
              <th>Исходная валюта</th>
              <th>Целевая валюта</th>
              <th>Исходная сумма</th>
              <th>Получаемая сумма</th>
              <th>Дата</th>
            </tr>
          </thead>
          <tbody>
            {history.map(
              ({ id, srcValute, dstValute, srcValue, dstValue, date }) => (
                <tr key={id}>
                  <td>{srcValute}</td>
                  <td>{dstValute}</td>
                  <td>{srcValue}</td>
                  <td>{dstValue}</td>
                  <td>{date}</td>
                </tr>
              )
            )}
          </tbody>
        </Table>
      )}
      <Button
        onClick={async () => {
          const query = qs.stringify(
            {
              srcValute: from,
              dstValute: to,
              date: new Date().toISOString(),
            },
            { addQueryPrefix: true }
          );
          const responseRaw = await fetch(`${API_URL}/valute/history${query}`);
          const response = await responseRaw.json();
          setHistory(response);
        }}
        variant='primary'
      >
        Поиск
      </Button>
    </div>
  );
};

export default History;
