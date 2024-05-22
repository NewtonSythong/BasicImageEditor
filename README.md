# TEAM X README 

## Feature Contributors
### Part 1
- Sharpen Filter - Katrina
- Gaussian Blur Filter - Jenny
- Median Filter - Cam & Jenny
- Image Inversion - Katrina
- Colour Channel Cycling - Katrina
- Multilingual Support - Newton & Jenny
- Image Resize - Tele
- Image Rotation - Newton
- Image Flip - Newton
- Image Export - Jenny
- Exception Handling - Cam & Tele & Jenny
- Other Error avoidance/prevention - Cam & Jenny
### Part 2
 - Extended Filters - Jenny
 - Filters with Negative Results - Jenny
 - Emboss and Edge Detection - Jenny
 - Brightness adjustment - Katrina 
 - Contrast adjustment - Katrina 
 - Block Averaging - Newton 
 - Random Scattering - Jenny
 - Toolbar Operations - Cam
 - Keyboard Shortcuts - Cam
 - Mouse Selection - Tele
 - Crop Selection - Tele
 - Drawing Functions - Tele
 - Macro for Record and Replay - Cam
 - Show us Something (Themes) - Katrina


  
## How was code tested 
For the image inversion action the output was tested against a online image inversion and was continually refined until they looked identical. Throughout the project, code was tested with the use of println statements to see the output. Alongside the use of print statements a large portion of testing was performed through trial and error of running the ANDIE program and seeing the outcome of using different parts of the GUI.

## Known Bugs
- Currently, the application restarts when the language is changed, so any image that is open or edited is lost
- When selecting a region of the image, areas beyond the image are able to be selected

## Brief User Guide 
Once the user has ran the application from within the Gradle projects a new window will appear. This is the ANDIE GUI (Graphical User Interface). Within this GUI there are 10 options in the menu.
As ANDIE is a Non-destructive image editor we suggest the user to open a file of type '.png'. Once opened a file the user has complete freedom with how they decide to experience ANDIE. Each menu bar represents a different set of features such as View, Filters, Colour and Image. If the user applies a unwanted feature then the edit menu bar is helpful as it has the ability to undo and redo actions. If the image has reached its desired state then the user should 'save as' if wanting to save as an operations file 'ops' or export if wanting to create a copy of exsisting image without the change history.

## No Significant Code Refactoring


## Use of Icons
Icons were used from the following open license location:
https://uxwing.com/ The license for use can be found here https://uxwing.com/license/.