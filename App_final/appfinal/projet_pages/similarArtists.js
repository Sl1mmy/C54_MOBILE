import React from 'react';
import {
  StyleSheet,
  View,
  FlatList,
  Text,
  Image,
  TouchableOpacity,
  Linking,
} from 'react-native';

function Item ({ title, image, url} ){
    return (
        <View style={styles.item}>
            <TouchableOpacity onPress={()=> {Linking.openURL(url)}}>
                <Image style={styles.image} source={{uri: image}}/>
            </TouchableOpacity>
            <Text style={styles.title}>{title} </Text>
        </View>
    );
}

const artistData = require("../../appfinal/projet_jsons/similarArtists.json");
var data = [];

for (let i = 0; i < Object.keys(artistData.artists).length; i++) {
    data.push({id:i,
        title:artistData.artists[i].name, 
        image:artistData.artists[i].images[1].url,
        url:artistData.artists[i].external_urls.spotify}); 
}

const renderItem = ({ item }) => (
    <Item title={item.title} image={item.image} url={item.url}/>
)

export default function SimilarArtists() {
  return (
    <View style={styles.container}>
      <FlatList 
        style={styles.flatlist}
        data={data}
        renderItem={renderItem}
      />
    </View>
  );
}

const styles = StyleSheet.create({
    flatlist: {
      width: 800 
    },
    
    item: {
      flex: 1,
      flexDirection : 'column',
      alignItems: 'center',
      justifyContent: 'center',
      gap : 10,

      marginTop: 50,
    },

    title: {
      fontSize: 15,
    
    },
  
    image: {
      height: 200,
      width: 200,
      borderRadius: 25,
    }, 
  
    container : {
      flex: 1,
      backgroundColor: 'black',
      alignItems: 'center',
      justifyContent: 'center',
    }
  });