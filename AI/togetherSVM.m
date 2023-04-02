clear;close all; clear all; clc;

% load dataset
data=readtable('togetherapp.csv');

% Create a copy of the same dataset
DataC=data;

% Checking the Null values
disp(sum(ismissing(data)));

% Replacing text label with numbers
k=["Not Depressed","Depressed", "Severely Depressed"];
l=[0,1,2];
g=DataC.label;

% generete vector of 0,1 and 2 values
number=zeros(length(g),1);
for i=1:length(k)
rs=ismember(g,k(i)); number(rs)=l(i);
end
DataC.label=number;

% Split 80% for training and 20% for testing
cv = cvpartition(size(DataC, 1), 'HoldOut', 0.2);  % 20 for test
idx = cv.test;

% Create Training data and Training target
dataTrain = DataC(~idx,:);
TrainingData=table2array(dataTrain(:,1:end-1)); % creating dataset by removing the label data column
TrainingTarget=table2array(dataTrain(:,end)); %  Training target

% Create Testing data and Testing target
dataTest = DataC(idx,:);
TestingData=table2array(dataTest(:,1:end-1));
TestingTarget=table2array(dataTest(:,end)); %  Testing target

% Train the model using training dataset
mdl = fitcecoc(TrainingData,TrainingTarget);

% predict the response using the trained model
out = predict(mdl,TestingData);
% out = kfoldPredict(crossval(mdl));

% calculate the resubstitution error
resuberror = resubLoss(mdl)

% Calculate the accuracy of the trained model
accuracy = sum(out == TestingTarget)/numel(TestingTarget)*100;

% Calculate the performance of the trained model
C = confusionmat(TestingTarget,out);
precision = C(2,2)/(C(2,2)+C(1,2))*100;
recall = C(2,2)/(C(2,2)+C(2,1))*100;
f1score = 2*precision*recall/(precision+recall);

% Display the results
disp(['Accuracy: ', num2str(accuracy)]);
disp(['Precision: ', num2str(precision)]);
% disp(['Recall: ', num2str(recall)]);
% disp(['F1 Score: ', num2str(f1score)]);

% figure
% confusionchart(TestingTarget, out)
% title('Confusion Matrix')

%% Export the trained model to JSON format using JSONlab
jsonStr = savejson('', mdl); % empty string to convert entire model
fid = fopen('model.json', 'w'); % opened a file to write the model
fprintf(fid, '%s', jsonStr);
fclose(fid);


