const http = require('http');
const fs = require('fs');
const readline = require('readline');

const hostname = '127.0.0.1';
const port = 3000;

let nRows = 0;
let nWords = 0;
let maxWords = 0;
let rowMax = 0;
let wordsPerRows = [];

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html');

  let rl = readline.createInterface({
    input: fs.createReadStream("myFile.txt"),
    output: process.stdout,
    terminal: false 
  });
  rl.on('line', readLineCallback);
  rl.on('close', () => endFileCallBack(rl, res));
  rl.on('error', (err) => {
    // Handle error if the file cannot be read
    res.statusCode = 500;
    res.end('Error reading file');
  });
});

function readLineCallback(line) {
  console.log("line contents", line);
  let nWordsSing = line.trim().split(/\s+/).length;
  nRows++;
  nWords = nWords + nWordsSing;
  wordsPerRows.push(nWordsSing);
  if (nWordsSing > maxWords)
    maxWords = nWordsSing;
  for (let i = 0; i < wordsPerRows.length; i++)
    if (wordsPerRows[i] === maxWords)
      rowMax = i;
}

function endFileCallBack(rl, res) {
  rowMax++;
  let result = '<label for="res">Numero righe: </label><input type="text" readonly ' + "value='" + nRows + "'"  + '</input><br>' +
  '<label for="res">Numero totale parole: </label><input type="text" readonly ' + "value='" + nWords + "'"  + '</input><br>' +
  '<label for="res">Riga con max parole: </label><input type="text" readonly ' + "value='" + rowMax + "'"  + '</input><br>';
  rl.close();
  for (let i = 0; i < wordsPerRows.length; i++)
    result = result + '<label for="res">Riga ' + (i+1) + ' ha queste parole: </label><input type="text" readonly ' + "value='" + wordsPerRows[i] + "'"  + '</input><br>';
  res.end(result);
}

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});