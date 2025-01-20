const http = require('http');
const fs = require('fs');

const hostname = '127.0.0.1';
const port = 3000;

const server = http.createServer((req, res) => {
  res.statusCode = 200;
  res.setHeader('Content-Type', 'text/html');
  fs.readFile("myFile.txt", (error, dataBuffer) => readFileCallback(error, dataBuffer, res)); // Inizio lettura
});

function readFileCallback(error, dataBuffer, res) {
  // convenzione Node per callback: primo argomento è oggetto
  if (error) {
    res.statusCode = 500;
    res.end('Error reading file');
    return;
  }
  console.log("smallFile contents", dataBuffer.toString());
  let doc = dataBuffer.toString();
  let nWords = doc.trim().split(/\s+/).length;
  res.end('<label for="res">Numero parole: </label><input type="text" readonly ' + "value='" + nWords + "'"  + '</input>');
}

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});