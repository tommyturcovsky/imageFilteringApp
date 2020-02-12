README.md

"How to run"

To run this program with a script you need a text file named "input.txt". This file must be in the same folder as the jar file. In the text file ("input.txt") you will have a list of commands similar to the example below. Or you can run the jar file with the "-interactive" command to open the GUI for the program.

Once this program is open, if you want to create a rainbow or a checkerboard pattern then there is a "Generate Image" button you may click then choose which of those options you would like. If you want to manipulate an existing image* using the GUI, the first thing you need to do is click the "Open" button and choose the image you would like to alter. Once you have selected your image, it will appear in a view window on the right side of the program. There is a dropdown with all of the methods we provide for changing that image such as "Blur", "Greyscale", or "Mosaic". After you chose the desired method, click the "GO" button. The image on the right side will then be updated to reflect the desired alteration chosen. If you want to run the program using batch commands then click the "Batch Commands" button and a text field will appear where you will need to input the desired commands similar to the example shown below. If you would like to stop using batch commands and use the GUI click the "Apply Filter" button and it will restore the dropdown menu with the options to effect the image. When you are satisfied with your image you may click the "Save" button and choose where you want to save and the name of your picture.

load dog.jpg

blur

save dog-blur.jpg

load studio.jpg

sharpen

save studio-sharpen.jpg

blur

save studio-sharpen-blur.jpg

load dog.jpg

mosaic 5000

save dog-mosaic.jpg
"blur" is one of the commands that our program offers. The other commands offered are sharpen, greyscale, sepia, dither, and mosaic.

dog.jpg and studio.jpg are the filenames of the images we are wanting to change. These files need to be in the same folder that the jar file is in.

Run the jar file through the command line like it says to do in the assignment description. open a command-prompt/terminal and navigate to the folder containing the jar file. Now type java -jar NameOfJarFile.jar any-command-line-arguments-separated-by-spaces . This will cause the program to run using the text file.

Or you can use the command java -jar Program.jar -interactive . This will cause the program to open and let the user interact with and use the GUI to run the program.

"Design Decisions"

The parts of the assignment that are complete are all of the filter operations and color transformations along with the controller and the view.

We added an interface to our Model "Image.java" so that it can implement the observer pattern as the subject and the Controller "ImageController.java" as the observer.

The model of out MVC is out "Image.java" class and implements the “ImageInterface.java”. Image is responsible for altering all the images. The controller of our program is "ImageController.java" class and implements "Features.java" interface. The view of our program is "View.java" and implements "ViewInterface.java" as its interface.

*This program will only alter image files such as .jpg or .png

"Photo Citations"

The two photos we are submitting that can be used as input on this assignment are from Tommy T. and he has given permission to use them for this assignment. They are dog.jpg and studio.jpg.
