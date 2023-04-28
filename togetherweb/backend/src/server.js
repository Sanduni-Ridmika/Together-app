const express = require('express');
const cors = require('cors');
const bodyParser = require('body-parser');
const csv = require('csv-parser');
const tf = require('@tensorflow/tfjs-node');

const app = express();

// Middleware to parse JSON request bodies
app.use(express.json());

app.use(cors({
  credentials: true,
  origin: ['http://localhost:4200']
}));

app.get('/', (req, res) => {
  res.send('Welcome to the API');
  // const responses = [1, 3, 2, 1, 2, 1, 2, 3, 1, 3, 3];
});

// Load the trained model
async function loadModel() {
  const model = await tf.loadLayersModel('file://model.h5');
  return model;
}
const modelPromise = loadModel();

// Parse incoming request bodies as JSON
app.use(bodyParser.json());


// Handle POST requests to the '/predict' endpoint
app.post('/predict', async (req, res) => {
  // Parse the user response from the request body
  // const userResponse = req.body;
  
  const model = await modelPromise;

  const input = [3,3,3,3,3,3,3,2,2,3,2];

  // Convert the response to a numerical array
 /* const input = Object.values(userResponse).map(val => {
    if (val === 'Not at all') return 0;
    if (val === 'Several days') return 1;
    if (val === 'More than half the days') return 2;
    if (val === 'Nearly every day') return 3;
  }); */

  // Make a prediction using the loaded model
  const prediction = model.predict(tf.tensor([input]));

  // Get the predicted label
  const predictedLabel = prediction.argMax(axis=1).dataSync()[0];

  // Send the predicted label back to the frontend
  res.send({ predictedLabel });
});

const port = 5000;
app.listen(port, () => {
    console.log("Website served on http://localhost:" + port);
}) 

// res.send(`Predicted depression level: ${output}`); 
