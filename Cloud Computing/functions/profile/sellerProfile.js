// functions/profile/sellerProfile.js
const functions = require('firebase-functions');
const admin = require('firebase-admin');

module.exports = (app) => {
  return functions.https.onRequest(async (req, res) => {
    try {
      const sellerId = req.params.sellerId;

      switch (req.method) {
        case 'GET':
          if (sellerId) {
            // GET by ID
            const profileSnapshot = await admin.database().ref(`profile_penjual/${sellerId}`).once('value');
            const profile = profileSnapshot.val();

            res.status(200).json({
              status: 'success',
              message: 'Seller profile retrieved',
              data: [profile],
            });
          } else {
            // GET all
            const profilesSnapshot = await admin.database().ref('profile_penjual').once('value');
            const profiles = profilesSnapshot.val();

            const profilesArray = Object.keys(profiles).map(key => profiles[key]);

            res.status(200).json({
              status: 'success',
              message: 'All seller profiles retrieved',
              data: profilesArray,
            });
          }
          break;

        case 'POST':
          // POST
          const newProfileData = req.body;
          const newProfileRef = admin.database().ref('profile_penjual').push();
          await newProfileRef.set(newProfileData);

          res.status(201).json({
            status: 'success',
            message: 'Seller profile created',
            data: [{
              sellerId: newProfileRef.key,
              ...newProfileData,
            }],
          });
          break;

        case 'PUT':
          // UPDATE
          const updatedProfileData = req.body;
          await admin.database().ref(`profile_penjual/${sellerId}`).update(updatedProfileData);

          res.status(200).json({
            status: 'success',
            message: 'Seller profile updated',
            data: [{
              sellerId,
              ...updatedProfileData,
            }],
          });
          break;

        case 'DELETE':
          // DELETE
          await admin.database().ref(`profile_penjual/${sellerId}`).remove();

          res.status(200).json({
            status: 'success',
            message: 'Seller profile deleted',
            data: [{
              sellerId,
            }],
          });
          break;

        default:
          res.status(405).json({
            status: 'error',
            message: 'Method Not Allowed',
          });
      }
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
