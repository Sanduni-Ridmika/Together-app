import sys
import pickle
import numpy as np

# Load the model from the file
filename = 'modelTF.pkl'
with open(filename, 'rb') as file:
    model = pickle.load(file)

# Get the input from the command line arguments
input = sys.argv[1]
#input = np.fromstring(input, dtype=int, sep=',')
#input = input.reshape(1, -1)

#input = np.array([[2, 1, 1, 2, 3, 3, 2, 1, 0, 1, 1]])

# Make the prediction and print the result
prediction = model.predict([input])[0]
if prediction == 0:
    print("You are not depressed!")
elif prediction == 1:
    print("You are having depression.")
else:
    print("You are experiencing severe depression. Please seek professional help.")
# print(prediction)
