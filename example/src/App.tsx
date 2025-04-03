import { multiply, showToast } from 'react-native-awesome-library-ln';
import { Text, View, StyleSheet, TouchableOpacity } from 'react-native';
import { useState, useEffect } from 'react';

export default function App() {
  const [result, setResult] = useState<number | undefined>();

  useEffect(() => {
    multiply(3, 7).then(setResult);
  }, []);

  const isshowToast = (a: string) => {
    console.log(a, '0000000000000000');
    showToast(a);
  };
  return (
    <View style={styles.container}>
      <TouchableOpacity
        style={styles.containera}
        onPress={() => isshowToast('Hello from React Native!')}
      >
        <Text>Click me</Text>
      </TouchableOpacity>
      <Text onPress={() => showToast('Hello from React Native!')}>
        Result: {result}
      </Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  containera: {
    backgroundColor: 'red',
    width: 100,
    height: 100,
  },
});
