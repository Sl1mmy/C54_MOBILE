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

function Item ({ title, date, image, url } ){
    return (
        <View style={styles.item}>
            <TouchableOpacity onPress={()=> {Linking.openURL(url)}}>
                <Image style={styles.image} source={{uri: image}}/>
            </TouchableOpacity>
            <Text style={styles.title}>{title} </Text>
            <Text>{date} </Text>
        </View>
    );
}

artistData = require("../../appfinal/projet_jsons/albums.json");
var data = [];

for (let i = 0; i < artistData.total; i++) {
    data.push({id:i,
        title:artistData.items[i].name, 
        date:artistData.items[i].release_date, 
        image:artistData.items[i].images[1].url,
        url:artistData.items[i].external_urls.spotify});
}


const renderItem = ({ item }) => (
    <Item title={item.title} date={item.date} image={item.image} url={item.url} />
)

export default function Albums() {
  console.log(data);
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

    artistText: {
      color : '#fff',
      fontSize : 20,
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