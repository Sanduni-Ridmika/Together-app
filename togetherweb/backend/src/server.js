const express = require('express');
const cors = require('cors');

const app = express();

app.use(cors({
  credentials: true,
  origin: ['http://localhost:4200']
}));

app.get("/",(req,res) => {
    res.send("hello world");
})

const port = 5000;
app.listen(port, () => {
    console.log("Website served on http://localhost:" + port);
})

//Imported machine learning model
const model = require('/Users/Sanduni Ridmika/Desktop/FYP/AI/model.json');
