import { authorize } from 'react-native-app-auth';

const config = {
  clientId: '09d95d1972da46ceaefa16e4b1f3af1d',
  clientSecret: 'a29277828c81411fa6689c4882edf915',
  redirectUrl: 'com.appfinal://oath',
  scopes: ['user-read-email', 'user-read-private'],
  serviceConfiguration: {
    authorizationEndpoint: 'https://accounts.spotify.com/authorize',
    tokenEndpoint: 'https://accounts.spotify.com/api/token',
  },
};

export const authenticateSpotify = async () => {
  try {
    console.log('demarrer lauthentification...');
    const result = await authorize(config);
    // console.log('resultat:', result);
    return result.accessToken;
  } catch (error) {
    console.error('erreur:', error);
  }
};
