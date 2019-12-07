import RPi.GPIO as GPIO
import time
import requests
import json

red = 19
green = 26
blue = 13
try:
    GPIO.setmode(GPIO.BCM)
    GPIO.setup(red, GPIO.OUT)
    GPIO.setup(green, GPIO.OUT)
    GPIO.setup(blue, GPIO.OUT)

    while True:
        req = requests.get('https://1.1.1.1:4567/',verify=False).text
        state = json.loads(req)
        print(state)
        GPIO.output(red,state["red"])
        GPIO.output(green,state["green"])
        GPIO.output(blue,state["blue"])
        time.sleep(5)

except KeyboardInterrupt:
    GPIO.cleanup()
