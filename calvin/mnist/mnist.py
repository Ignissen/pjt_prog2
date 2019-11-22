import tensorflow as tf
import matplotlib.pyplot as plt
import matplotlib.image as mpimg
import numpy as np
from PIL import Image
import os
import sys

(x_train, y_train), (x_test, y_test) = tf.keras.datasets.mnist.load_data()

x_train.shape

# Reshaping the array to 4-dims so that it can work with the Keras API
x_train = x_train.reshape(x_train.shape[0], 28, 28, 1)
x_test = x_test.reshape(x_test.shape[0], 28, 28, 1)
input_shape = (28, 28, 1)
# Making sure that the values are float so that we can get decimal points after division
x_train = x_train.astype('float32')
x_test = x_test.astype('float32')
# Normalizing the RGB codes by dividing it to the max RGB value.
x_train /= 255
x_test /= 255
print('x_train shape:', x_train.shape)
print('Number of images in x_train', x_train.shape[0])
print('Number of images in x_test', x_test.shape[0])

# Importing the required Keras modules containing model and layers
from keras.models import Sequential
from keras.layers import Dense, Conv2D, Dropout, Flatten, MaxPooling2D
# Creating a Sequential Model and adding the layers
model = Sequential()
model.add(Conv2D(28, kernel_size=(3,3), input_shape=input_shape))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Flatten()) # Flattening the 2D arrays for fully connected layers
model.add(Dense(128, activation=tf.nn.relu))
model.add(Dropout(0.2))
model.add(Dense(10,activation=tf.nn.softmax))

model.compile(optimizer='adam', 
              loss='sparse_categorical_crossentropy', 
              metrics=['accuracy'])



if(os.path.exists("model.h5") and len(sys.argv) == 1):
    model = tf.keras.models.load_model("model.h5")
else:
    model.fit(x=x_train,y=y_train, epochs=int(sys.argv[1]))

model.evaluate(x_test, y_test)

image_index = 4444
image_to_predict = x_test[image_index]
image_to_predict = Image.open("5_handdrawn.png").convert("L")
image_to_predict = np.resize(image_to_predict, (28,28,1))
image_to_predict = np.array(image_to_predict)
image_to_predict = image_to_predict.reshape(1,28,28,1)
correct_answer = y_test[image_index]
correct_answer = 5

pred = model.predict(image_to_predict)

print(pred.argmax())
print(correct_answer)

imgplot = plt.imshow(image_to_predict.reshape(28, 28),cmap='Greys')
plt.show()
model.save("model.h5")