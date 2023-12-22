/**
 * Import function triggers from their respective submodules:
 *
 * const {onCall} = require("firebase-functions/v2/https");
 * const {onDocumentWritten} = require("firebase-functions/v2/firestore");
 *
 * See a full list of supported triggers at https://firebase.google.com/docs/functions
 */

const functions = require('firebase-functions');
const admin = require('firebase-admin');

const app = admin.initializeApp();

const sellerProfile = require('./profile/sellerProfile')(app);
const getSearch = require('./search/getSearch')(app); // Add this line


exports.sellerProfile = functions.https.onRequest(sellerProfile);
exports.getSearch = functions.https.onRequest(getSearch); // Add this line

