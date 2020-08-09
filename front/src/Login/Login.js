import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Form, Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.css';
import './Login.css';
import { API_URL } from '../constants';

const Login = () => {
  const history = useHistory();
  const [username, setUsername] = useState();
  const [password, setPassword] = useState();

  return (
    <div className='wrapper'>
      <Form>
        <Form.Group>
          <h3 className='main-title'>Конвертер валют</h3>
          <Form.Control
            onChange={({ target: { value } }) => setUsername(value)}
            type='login'
            placeholder='Введите логин'
          />
        </Form.Group>

        <Form.Group>
          <Form.Control
            onChange={({ target: { value } }) => setPassword(value)}
            type='password'
            placeholder='Введите пароль'
          />
        </Form.Group>

        <Button
          className='login-button'
          onClick={async () => {
            const response = await fetch(`${API_URL}/login`, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json; charset=utf-8',
              },
              body: JSON.stringify({ username, password }),
            });

            if (response && response.status === 200) {
              history.push('/converter');
            }
          }}
          variant='primary'
        >
          Войти
        </Button>
      </Form>
    </div>
  );
};

export default Login;
