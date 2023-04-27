const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();

app.use(cors({
  credentials: true,
  origin: ['http://localhost:4200']
}));

app.get('/', (req, res) => {
  res.send('Welcome to the API');
  const responses = [1, 3, 2, 1, 2, 1, 2, 3, 1, 3, 3];
});

const port = 5000;
app.listen(port, () => {
    console.log("Website served on http://localhost:" + port);
}) 

// res.send(`Predicted depression level: ${output}`); 
