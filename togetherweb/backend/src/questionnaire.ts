/*import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-phq9',
  templateUrl: './phq9.component.html',
  styleUrls: ['./phq9.component.css']
})
export class Phq9Component {
  questions = [
    { id: 'q1', text: 'Little interest or pleasure in doing things' },
    { id: 'q2', text: 'Feeling down, depressed, or hopeless' },
    { id: 'q3', text: 'Trouble falling or staying asleep, or sleeping too much' },
    { id: 'q4', text: 'Feeling tired or having little energy' },
    { id: 'q5', text: 'Poor appetite or overeating' },
    { id: 'q6', text: 'Feeling bad about yourself — or that you are a failure or have let yourself or your family down' },
    { id: 'q7', text: 'Trouble concentrating on things, such as reading the newspaper or watching television' },
    { id: 'q8', text: 'Moving or speaking so slowly that other people could have noticed? Or the opposite — being so fidgety or restless that you have been moving around a lot more than usual' },
    { id: 'q9', text: 'Thoughts that you would be better off dead or of hurting yourself in some way' }
  ];

  answers = [
    'Not at all',
    'Several days',
    'More than half the days',
    'Nearly every day'
  ];

  response = {};

  constructor(private http: HttpClient) { }

  onSubmit() {
    // Send the user response to the backend for prediction
    this.http.post<{ predictedLabel: number }>('http://localhost:3000/predict', this.response).subscribe(data => {
      // Display the predicted label to the user
      alert(`Your predicted depression level is: ${data.predictedLabel}`);
    });
  }
}
*/