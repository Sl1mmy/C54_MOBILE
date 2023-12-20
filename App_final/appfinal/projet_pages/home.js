import React, {useState, useEffect} from 'react';
import { authenticateSpotify } from '../SpotifyAuth';
import {
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
  Image,
  Button,
  Linking,
} from 'react-native';

export default function Home({navigation}) {
  const [artistData, setArtistData] = useState(require("../../appfinal/projet_jsons/artist.json"));

  /*
  const [token, setToken] = useState(authenticateSpotify());
  console.log(token);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch('https://api.spotify.com/v1/artists/1G0YV9WooUBjrwDq0Q7EFK', { method: 'GET',
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },} );
  
        const data = await response.json();
        setArtistData(data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, [token]); // Dependency array to re-run the effect when the token changes

  setArtistData(data);
  */

  let artistName = artistData.name;
  let artistImageLink = artistData.images[0].url;
  let artistUrl = artistData.external_urls.spotify;
  
  return (
    <View style={styles.container}>
      <View style={styles.artistContainer}>
        <Text style={styles.artistText}> {artistName} </Text>
        <TouchableOpacity onPress={()=> {Linking.openURL(artistUrl)}}>
            <Image style={styles.image} source={{uri: artistImageLink}}/>
        </TouchableOpacity>
      </View>

      <View style={styles.buttonContainer}>
        <Button onPress={() => navigation.navigate('Albums')} title='Ses albums'/>
        <Button onPress={() => navigation.navigate('SimilarArtists')} title='Ses artistes similaires'/>
      </View>
    </View>
  );
}  

/* feuille de style ReactNative style */
const styles = StyleSheet.create({
  artistText: {
    color : '#fff',
    fontSize : 20,
  },

  image: {
    height: 320,
    width: 320,
    borderRadius: 25,
  },

  artistContainer: {
    flexDirection : 'column',
    alignItems: 'center',
    justifyContent: 'center',
    gap : 15,
  },

  buttonContainer: {
    flexDirection : 'row',
    gap : 15,

    marginTop : 60,
  },  

  container : {
    flex: 1,
    backgroundColor: 'black',
    alignItems: 'center',
    justifyContent: 'center',
  }
});