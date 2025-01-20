const http = require('http');
const fs = require('fs');
const readline = require('readline');
const writeline = require('writeline');
const url = require('url');

const hostname = '127.0.0.1';
const port = 3000;

let nRows = 0;
let nWords = 0;
let maxWords = 0;
let rowMax = 0;
let wordsPerRows = [];
let fileName = "";

let word = "";
let nOcc = 0;

const server = http.createServer((req, res) => {
  // Handle favicon request explicitly
  if (req.url === '/favicon.ico') {
    res.statusCode = 204;  // No Content
    res.end();
    return;
  }

  //resetto i conteggi:
  nRows = 0;
  nWords = 0;
  maxWords = 0;
  rowMax = 0;
  wordsPerRows = [];
  
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html');

  const fullUrl = `http://${hostname}:${port}${req.url}`;
  fileName = url.parse(fullUrl, true).query.name;
  word = url.parse(fullUrl, true).query.word;  
  console.log(`http://${hostname}:${port}/` + req.url);

  let rl = readline.createInterface({
    input: fs.createReadStream(fileName),
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
  for (let i = 0; i < line.trim().split(/\s+/).length; i++)
    if (line.trim().split(/\s+/)[i] === word)
      nOcc++;
}

function writeLineCallback(line) {
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
  for (let i = 0; i < line.trim().split(/\s+/).length; i++)
    if (line.trim().split(/\s+/)[i] === word)
      nOcc++;
}

function trueEndFileCallBack(line) {
  rowMax++;
  let result = '<p>' + fileName +'</p><br>' + 
  '<label for="res">Numero righe: </label><input type="text" readonly ' + "value='" + nRows + "'"  + '</input><br>' +
  '<label for="res">Numero totale parole: </label><input type="text" readonly ' + "value='" + nWords + "'"  + '</input><br>' +
  '<label for="res">Riga con max parole: </label><input type="text" readonly ' + "value='" + rowMax + "'"  + '</input><br>';
  for (let i = 0; i < wordsPerRows.length; i++)
    result = result + '<label for="res">Riga ' + (i+1) + ' ha queste parole: </label><input type="text" readonly ' + "value='" + wordsPerRows[i] + "'"  + '</input><br>';
  res.end(result);
}

function endFileCallBack(rl, res) {
  rl.close();

  let wl = writeline.createInterface({
    input: fs.createWriteStream(fileName),
    output: process.stdout,
    terminal: false 
  });

  rl.on('line', writeLineCallback);
  rl.on('close', () => trueEndFileCallBack(rl, res));
  rl.on('error', (err) => {
    // Handle error if the file cannot be read
    res.statusCode = 500;
    res.end('Error writing file');
  });
}

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});