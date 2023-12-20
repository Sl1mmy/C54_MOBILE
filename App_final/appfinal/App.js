import React from 'react';
import { StyleSheet } from 'react-native';
import {NavigationContainer} from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';

import Home from './projet_pages/home';
import Albums from './projet_pages/albums';
import SimilarArtists from './projet_pages/similarArtists';

const Stack = createNativeStackNavigator();

function App() {
  return(
  <NavigationContainer>
      <Stack.Navigator screenOptions={{headerShown: false}}>
        <Stack.Screen name="Home" component={Home}/>
        <Stack.Screen name="Albums" component={Albums}/>
        <Stack.Screen name="SimilarArtists" component={SimilarArtists}/>
      </Stack.Navigator>
  </NavigationContainer>
  )
}

const styles = StyleSheet.create({
  
});

export default App;