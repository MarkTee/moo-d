const functions = require('firebase-functions');

// The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
admin.initializeApp();
const db = admin.firestore();

// Test hello world function
exports.helloWorld = functions.https.onRequest((req, res) => {
  res.send("Test 2");
});

async function iterateThroughMoods(userId, callbackEachMood) {
  console.log(`Getting moods for 'users/${userId}/moods'`)
  const data = await db.collection(`users/${userId}/moods`).get();
  const owner = (await db.doc(`users/${userId}`).get()).data();
  data.forEach(d => {
    console.log(`Got a mood for ${userId}: ${d.id}`);
    let data = d.data();
    data.owner = {
      id: userId,
      username: owner.username
    }
    callbackEachMood(data);
  })
}

exports.getFolloweeMoods = functions.https.onRequest(async (req, res) => {
  const uid = "100131882670449060318";
  const data = await db.doc(`users/${uid}`).get();
  const user = data.data();

  const moods = [];
  if (user.following) {
    let promises = user.following.map(uid => iterateThroughMoods(uid, (mood) => moods.push(mood)));
    await Promise.all(promises);
  } else {
    console.log("No followees found");
  }

  res.send(moods);
});

