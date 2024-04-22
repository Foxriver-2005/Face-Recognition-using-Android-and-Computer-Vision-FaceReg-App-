#To the test the performance of the Facenet_keras.h5#
Follow this link to download the facenet_keras_weights.h5 
*https://drive.google.com/file/d/1Zuxu-QVmuPxpnVAcsxt8g8LIz1TnxyC3/view?usp=sharing*
After downloading place the file inside the folder "OpenCV"
The create the other folder inside the "Faces" folder for example Felix and paste the images of Felix inside the folder you just created.
Now run the "train_v2.py" to train the model, the training may take long depending on the number of Folders inside the "Faces" folder, the more the images to train, the longer it take.
After the training is complete, now run the "detect.py" infront of people of people you train the model with for the to be recognized, anyone who wasn't in training should be recognized as uknown.

#CONVERSION OF Facenet_keras to MobileFaceNet.tflite#
This method is option because i have already created the "MobileFaceNet.tflite" and placed inside the Folder "/app/src/main/assets/".
This option also wouldn't work if you don't have enough computing resources like GPU, best run if you have GPU in your local environment.
Follow the link below to download the "Faces ms1m-refine-v2_112x112 TFRecord": 
*https://www.kaggle.com/code/jasonhcwong/mobilefacenet*
After downloading, extract and create a folder named "Faces ms1m-refine-v2_112x112 TFRecord" and paste all the file in that folder, before running the "toTFLite.ipynb"

##To run the android app (.apk) ##
Download the file "FaceReg.apk" and install in your android phone,
Then run the app,
Register using your email, and set your password (recommende to use real email just incase you forgot your password that way you can reset your password.
Then proceed to login into the system.
After login
1. press register button on the home screen.
2. Choose where from the option where you want your face to be registered from (camera or Gallery).
3. The fill in the name of the face to be registered in the dialog given and press okay.
   The system will first check if the face exist in the face you want to register first, the proceed to check if there is more than one face in the image you want registered also.
   You cannot register from an image with more than one face or no face at all.
   If the above conditions is meet the system will crop the image into 112 * 112 * 3, then save the face. Only face will be registered.
4. To confirm the face is registered, click the "Show Faces" button at the right top corner of the home page to confirm if the face is registered successfully.
5. If you want to delete the registerd face, just tap on it and it will be deleted.
5. To run the Recognize
      Press the "Recognize" button
      Then choose from the options where you want your image to be recognized from.
      Then wait for a moment for the system to iterate the saved images to try and see if there is a match.
      If the match is found, the system will display the images in the home screen and the corresponding name.
      The system uses the euclidean distance to determine if it's a match.
6. To exit the system press the logout button.
             
