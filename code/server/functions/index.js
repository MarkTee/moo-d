const functions = require('firebase-functions');
const path = require('path');
const Moods = require(path.resolve( __dirname, "./moods.js" ));


// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp();
const db = admin.firestore();


exports.getFolloweeMoods = functions.https.onRequest(async (req, res) => {
  const uid = req.query.uid;

  const data = await db.doc(`users/${uid}`).get();
  const user = data.data();
  console.log(`Starting get Followee moods for user ${uid}`);

  const moods = [];
  if (user.following) {
    let promises = user.following.map(uid => Moods.iterateAllOfUser(db, uid, (mood) => moods.push(mood)));
    await Promise.all(promises);
  } else {
    console.log("No followees found");
  }

  res.send(moods);
});

exports.followUser = functions.https.onRequest(async (req, res) => {
  const uid = req.query.uid;
  const otherId = req.query.oid;

  const user = db.doc(`users/${uid}`);
  const userData = (await user.get()).data();
  userData.following = [...(new Set((userData.following || []).concat([otherId])))];
  user.set(userData);
  res.send("Success\n");
})

exports.unfollowUser = functions.https.onRequest(async (req, res) => {
  const uid = req.query.uid;
  const otherId = req.query.oid;

  const user = db.doc(`users/${uid}`);
  const userData = (await user.get()).data();

  const f = new Set(userData.following || []);
  if (f.has(otherId)) {
    f.delete(otherId);
  }

  userData.following = [...f];
  user.set(userData);

  res.send("Success\n");
})

