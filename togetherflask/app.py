from flask import Flask, render_template, request
import pickle

app = Flask(__name__)
model = pickle.load(open('model.pkl', 'rb'))

@app.route("/")
def home():
    result = ''
    return render_template("home.html", **locals())

@app.route('/predict', methods=['POST', 'GET'])
def predict():
    Q1 = float(request.form['Q1'])
    Q2 = float(request.form['Q2'])
    Q3 = float(request.form['Q3'])
    Q4 = float(request.form['Q4'])
    Q5 = float(request.form['Q5'])
    Q6 = float(request.form['Q6'])
    Q7 = float(request.form['Q7'])
    Q8 = float(request.form['Q8'])
    Q9 = float(request.form['Q9'])
    Q10 = float(request.form['Q10'])
    Q11 = float(request.form['Q11'])

    result_dict = {
        0: 'Not Depressed',
        1: 'Depressed',
        2: 'Severely Depressed'
    }
    result_num = model.predict([[Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11]])[0]
    result = result_dict[result_num]
    return render_template('home.html', **locals())


if __name__ == "__main__":
    app.run(debug=True)