Android-FontManager
==============
FontManager is a utility that makes it easier to work with fonts in Android.

## How it works

FontManagers loads fonts and converts them as typefaces, then allows you to apply them by either using the `ApplyFont` annotation or the `get` method.

## Implementation

FontManager's primary feature is the @ApplyFont annotation, just specify it along with the component you want to apply the font to:

	public class Main extends Activity {

		@ApplyFont(Fonts.AKKURAT_LIGHT)
	    TextView label;
	    

Then just call `apply` when your components has been initialized with the class containing the component.

	FontsManager.getInstance().apply(this);
	
You can also retrive the Typefaces through `get`.

	FontsManager.getInstance().get(Fonts.AKKURAT_LIGHT);

## Setting up:

1. First put your fonts in **assets/fonts**, I use two font files called Akkurat-Bold.otf and Akkurat-Light.otf. *(I have only worked with .otf files so far)*.
2. Create a new class that will hold the filenames for your fonts, I named mine Fonts.java

		package com.example.app;
		
		public class Fonts {
		    public static final String AKKURAT_BOLD = "Akkurat-Bold.otf";
		    public static final String AKKURAT_LIGHT = "Akkurat-Light.otf";
		}

3. Time to let FontManager know which fonts to work with, go to your application class and define them.

		se.marteinn.utils.fonts.fontmanager.FontManager;

    	@Override
    	protected void onCreate(Bundle savedInstanceState) {

			FontsManager.createInstance(this)
        			.define(new String[]{
            	    	Fonts.AKKURAT_BOLD,
	      	          	Fonts.AKKURAT_LIGHT
		   	     });
		}



## Setup

#### Locally

Download [fontmanager.aar](https://github.com/marteinn/Android-FontManager/blob/master/dist/fontmanager.aar) and move it to your libs folder, then make sure you got libs specified as a flatDir in `gradle.config`
	
	repositories {
	    mavenCentral()
	    flatDir {
	        dirs 'libs'
	    }
	}

After that just include it as any other dependency.
 
	dependencies {
		compile 'se.marteinn.utils.fonts:fontmanager:1.0-BETA@aar'
	}


## Contributing

Want to contribute? Awesome. Just send a pull request.

This project is setup as a Android Library for Android Studio. Use `make build` to build.


## License

Android-FontManager is released under the [MIT License](http://www.opensource.org/licenses/MIT).