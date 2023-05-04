const express = require('express');
const bodyParser = require('body-parser');
const {PythonShell} = require('python-shell');

const app = express();

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

// Endpoint to make predictions
app.post('/predict', (req, res) => {
  // Get the user input from the request body
  const input = req.body.input;

  // Call the Python script to make the prediction
  const options = {
    scriptPath: './',
    args: [input], // Pass the input as an argument to the Python script
  };
  PythonShell.run('model.py', options, (err, result) => {
    if (err) {
      console.log(err);
      res.status(500).send('An error occurred while making the prediction');
    } else {
      const prediction = result[0];
      res.send(`The predicted value is: ${prediction}`);
    }
  });
});

app.get('/', (req, res) => {
  res.send('Welcome to the API');
});

const port = 5000;
app.listen(port, () => {
    console.log("Website served on http://localhost:" + port);
}) 

/*
// Load the .pkl file containing the model
const modelData = fs.readFileSync('./model.pkl');
const model = pickle.load(modelData);

userInput = [3,3,3,3,3,3,3,2,2,3,2];

// Define a function to make predictions based on user input
function predictDepressionLevel(userInput) {
  // Do any necessary data preprocessing on the user input
  const prediction = model.predict(userInput);
  return prediction;
}

// Set up the Express app
const app = express();
app.use(bodyParser.urlencoded({ extended: true }));

// Define a route to handle POST requests with user input
app.post('/predict', (req, res) => {
  const userInput = req.body;
  const prediction = predictDepressionLevel(userInput);
  res.send(`The predicted depression level is: ${prediction}`);
});
*/