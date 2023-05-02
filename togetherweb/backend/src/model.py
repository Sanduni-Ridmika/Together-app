import sys
import pickle

# Load the model from the file
filename = 'model.pkl'
with open(filename, 'rb') as file:
    model = pickle.load(file)

# Get the input from the command line arguments
input = sys.argv[1]

# Make the prediction and print the result
prediction = model.predict([input])[0]
print(prediction)
