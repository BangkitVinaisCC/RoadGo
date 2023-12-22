// functions/search/getSearch.js
const functions = require('firebase-functions');
const admin = require('firebase-admin');
const { Storage } = require('@google-cloud/storage');

const storage = new Storage();
const bucket = storage.bucket('authlogin-78b44.appspot.com'); // Replace with your actual bucket name

module.exports = (app) => {
  return functions.https.onRequest(async (req, res) => {
    try {
      if (req.method !== 'POST') {
        res.status(400).json({
          status: 'error',
          message: 'Bad Request',
          errorDetails: 'Invalid request method. Use POST instead.',
        });
        return;
      }

      const inputText = req.body.text;

      if (!inputText) {
        res.status(400).json({
          status: 'error',
          message: 'Bad Request',
          errorDetails: 'Missing required parameter: text in the request body',
        });
        return;
      }

      // Download the ML model from Cloud Storage
      const modelFile = await bucket.file('path/to/authlogin-78b44.appspot.com/model_B4.json'); // Replace with your actual model path
      const modelContent = await modelFile.download();
      const model = JSON.parse(modelContent.toString());

      // Use your ML model to get the corrected name
      const outputText = model.correctName(inputText);

      console.log(`Search: ${inputText}`);
      console.log(`Did you mean: ${outputText}`);

      // Update the name in the Realtime Database under profile_penjual path
      const profileRef = db.ref('profile_penjual');
      profileRef.once('value', (snapshot) => {
        snapshot.forEach((childSnapshot) => {
          const seller = childSnapshot.val();
          if (seller.name === inputText) {
            // Update the name with the corrected name
            childSnapshot.ref.update({ name: outputText });
          }
        });
      });

      // You can also return the corrected name in the response
      res.status(200).json({
        status: 'success',
        message: 'Search result retrieved',
        data: {
          inputText,
          correctedName: outputText,
        },
      });
    } catch (error) {
      console.error(error);
      res.status(500).json({
        status: 'error',
        message: 'Internal Server Error',
        errorDetails: error.message,
      });
    }
  });
};
