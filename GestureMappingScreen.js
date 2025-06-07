import React, { useEffect } from 'react';
import { View, Text, Button, Alert, NativeModules, NativeEventEmitter } from 'react-native';

const { AccessibilityBridge, XboxController } = NativeModules;

const GestureMappingScreen = () => {
  useEffect(() => {
    const eventEmitter = new NativeEventEmitter(XboxController);
    const subscription = eventEmitter.addListener('XboxButtonEvent', (event) => {
      Alert.alert('Button Pressed', event);
      // Here you would map the button event to a gesture
    });

    return () => subscription.remove();
  }, []);

  const openAccessibilitySettings = () => {
    AccessibilityBridge.openAccessibilitySettings();
  };

  return (
    <View style={{ flex: 1, padding: 20, justifyContent: 'center', alignItems: 'center' }}>
      <Text style={{ fontSize: 20, marginBottom: 20 }}>Gesture Mapping</Text>
      <Button title="Enable Accessibility Service" onPress={openAccessibilitySettings} />
    </View>
  );
};

export default GestureMappingScreen;
