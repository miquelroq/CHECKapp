# Design Specifications

This file serves as a primary means to identify and record non-graphical data regarding the already designed Mock-Ups, such as fonts, text size and etc.

## All Screens
* Either center or remove the CHECKapp logo
* Top Bar is invisible but contains a back arrow
* Fonts: TODO: FILL IN HERE
	* Header 1 - 
	* Header 2 - 
	* Header 3 / Body - 
	* Header 1 Bold - 
	* Header 2 Bold - 

## Screen A (Loading Screen)

The loading logo should be a single ImageView containing both the logo and the text to be displayed

### **ImageView**
* Color - ```#ffffff```
* Position - Centered Vertically and Horizontally

### **Background** 
* Color - ```#466181```


## Screen B (Profile Selection)

### **Logo Upper Right Corner**
* Color - ```#466181```
* Position - 50dp from top, 50dp from right

### **Profile Container**
* BG Color - #ffffff
* Position - Centered Vertically and Horizontally
* Profiles
	* Equal spacing inbetween
	* Add new profile - Floating Action Button or programatically stage a fake profile with no name and a Plus Symbol as PFP


## Screen C (Register Form)

Selecting a Text Field automatically scrolls the window up

* Font - Header 3 / Body on each field
* Numeric Pad for the fields:
	* Age
	* Height
	* Weight


## Screen D (Main Menu)

### **Buttons**
* Font - Header 2 on each 
* Color - ```#466181```

### **Profile**
* Bottom Right Icon - preview logged in user Pfp
* Position - 50dp from bottom, 50dp from right

### **Cool Triangles**
* Drawables - ```#466181```


## Screen E (Registers)

### **TopBar**
* Back Arrow
* Font - Header 1

### **LinearLayout**
Generate a horizontal bar for each past register
* Font - Header 2
* Background Color - ```ccd5dd```


## Screen F (Diagnostic with RadioGroup)

### **Question number**
* Font - Header 2
* Background Color - ```#466181```

### **Radio Group**
* Font - Bold Body
* Font Color - ```#8bb3e1```
* Fill-in Circle Color - ```#8bb3e1```

### **Bottom Info**
* "Question"
	* Font - Bold Header 2
	* Font Color - ```#8bb3e1```

* Actual Question
	* Font - Header 2
	* Font Color - ```#ffffff```
	* Background Color - ```#466181```

* Skip Button
	* Font - Header 2
	* Font Color - ```#466181```
	* Background Color - ```#8bb3e1```


## Screen G (Diagnostic with ImageView)

### **Question number**
* Font - Header 2
* Background Color - ```#466181```

### **ImageView**
* Fetch it from API

### **Bottom Info**
* Title
	* Font - Bold Header 2
	* Font Color - ```#8bb3e1```

* Info
	* Font - Header 2
	* Font Color - ```#ffffff```
	* Background Color - ```#466181```

* Skip Button
	* Font - Header 2
	* Font Color - ```#466181```
	* Background Color - ```#8bb3e1```


## Screen G (Testing)

### **Testing Widget**
* Cool
* Follows ```#466181```, ```#8bb3e1```, ```#ffffff``` color scheme


## Screen H (Report)

### **Patient Name**
* Font - Header 2
* Font Color - ```#ffffff```
* Background Color - ```#466181```


### **Inside-Container Scrollable**

* Sympton Descriptor
	* Font - Header 2
	* Font Color - ```#466181```
	* Background Color - ```#ffffff```

* Graph
	* TBD

* Button
	* Font - Header 3 / Body
	* Font Color - ```#ffffff```
	* Background Color - ```#466181```

### **Bottom Nav**
* Font - Header 1
* Font Color - ```#ffffff```
* Background Color - ```#466181```
* Button
	* Font - Header 3 / Body
	* Font Color - ```#ffffff```
	* Background Color - ```#8bb3e1```


## Screen I (Profile)

### **Patient Name**
* Font - Header 1
* Font Color - ```#466181```

### **Details**
* Key
	* Font - Header 3 / Body
	* Font Color - ```#466181```

* Value
	* Font - Header 1
	* Font Color - ```#466181```
