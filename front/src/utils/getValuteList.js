import React from 'react';

export const valuteToString = (charCode, name) =>
  name ? `${charCode}(${name})` : `${charCode}`;

const getValuteList = ({ valutes = [], withNames = true }) =>
  valutes.map(({ id, charCode, name }) => (
    <option key={id}>
      {withNames ? valuteToString(charCode, name) : valuteToString(charCode)}
    </option>
  ));

export default getValuteList;
