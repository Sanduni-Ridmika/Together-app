import pandas as pd
import numpy as np
import tensorflow as tf
from tensorflow import keras
from sklearn.model_selection import train_test_split

# Load the PHQ9 dataset
phq9_df = pd.read_csv('togethersamples.csv')

# Convert the response labels to numerical values
# phq9_df['label'] = phq9_df['label'].map({'Not Depressed': 0, 'Depressed': 1, 'Severly Depressed': 2})

# Convert the questionnaire responses to numerical values
# phq9_df = phq9_df.replace({'Not at all': 0, 'Several days': 1, 'More than half the days': 2, 'Nearly every day': 3})

# Split the dataset into training and validation sets
X_train, X_val, y_train, y_val = train_test_split(phq9_df.iloc[:, :-1], phq9_df.iloc[:, -1], test_size=0.2, random_state=42)

# Define the model architecture
model = keras.Sequential([
    keras.layers.Dense(16, activation='relu', input_shape=(9,)),
    keras.layers.Dense(8, activation='relu'),
    keras.layers.Dense(3, activation='softmax')
])

# Compile the model
model.compile(optimizer='adam', loss='sparse_categorical_crossentropy', metrics=['accuracy'])

# Train the model
model.fit(X_train, y_train, epochs=50, batch_size=32, validation_data=(X_val, y_val))

# Save the model to a file
model.save('model.h5')
