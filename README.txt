HOW TO LAUNCH:

	>> The data must exist on the data base, there is a file englishcourse.sql that contain all the data necessary to launch the program.
	>> There is a file main class that should be launched (just run it :) !).



SOURCE CODE DESCRIPTION :

	Package DataClass : - User.java -> class that contains user registered informations ;
			    - Rule.java -> class to store english rules related to each lesson ;
               	      	    - Lesson.java -> contains lesson informations and dataSetChangedObserver that notify the observer when the lesson is finished ;
			    - Level.java -> has level information ;
			    - Exercise.java -> has informations about exercise and used to represent the default exercise(fill the blank) ;
			    - OptionList.java -> extends from class Exercise and represent the exercise 'Select from JComboBox' ;
			    - OtherTypes.java -> extends from class Exercise and represent the other types 'mistake', 'drag and drop', 'option write' ;
			    - FinalTest.java -> extends from Exercise and represent final test that has two types of questions(fill the blank, optionList) ;
			    - DataBaseConnection.java -> singelton class for our connection ;
			    - Mark.java -> Interface for composite design pattern contains 'getMark()' that every lesson and exercise should implement where the 
				exercise represent the leaf element and the lesson is the composite class ;
			    - Correction.java -> class that has static method that takes ArrayList<JComponents> ,JComponents represent user answer, this method
				correct the exercise and return the mark ;
	
	Package Container : - Container.java -> interface that has an abstract method 'getIterator()';
			    - MyIterator.java -> Generic that has 2 methods 'hasNext()','next()'  ;
			    - MyHashMap.java -> It's a hashmap that we have built to use our own iterator  ;

	Package HomeLevels : this package to present the levels from data base in the main page (Elementary, Intermediate, Advanced) ;
	
	Package DisplayLessonContent :  - GenericPanel.java -> generic class represent each data base item in the same way (GridLayout and buttons) ;
					- LessonPageContent.java -> represent lessons with exercise for each level ;
					- LessonPageHeader.java -> represent header ; Those two previous classes represent the page when the user choose the level ;

	Package ExercisesPanel : contain a panel classes each class show one type of exercise and each class take line and another parameter ,
				lines are strings that contains placeholder of JComponent when the user enter the answer ;
				- ChooseWordPanel.java -> take string array of lines and options ,options are array of word that the user must rewite in the gap ;
				- ExDnD.java -> class that take choices and put them on the top of the page , the user must click on the choice to pick it 
					and click again on the gap to place it ;
				- ExFillBlankPanel.java -> class of the default exercise to present a fill the blank type ;
				- ExListContentPanel.java -> class that replace the place holders with a JComboBox filled with options from the array in the constructor
					where the user choose one of the options ;
				- FinalTestPanel.java -> class that represent the lines each line on its own type, fill the blank or JComboBox ;
				- FindMistakePanel.java -> replaces a placeHolder by JTextField with no borders that contain the mistake and when the user detect the mistake
					 and click on this JTextField the bottom border will be visible ;
				- RuleContent.java -> Panel that takes 'MyHashMap' contains header paragraphs as key and content paragraphs as value ;
				- MainPanel.java -> is Generic Panel contain header and content Generic panel that represents each view of our application ;

	Package PanelPackage : contain all the panels of the logIn and signUp ;

	Package FlyWeight : contain 3 classes representing 3 levels that contains exercises and lessons for each level ;

	Package VariablePool : contain colorPool and fontPool for color and font used in the project instead of creating color and color each time needed ;

	Package Decorator : - Header.java -> is an interface that contains the method used in every decorator we have ;
			    - HeaderDecorator.java ->  Abstract class contains object of class header ;
		 	    - BaseHeader.java -> class represent the basic header for each exercise and rule ;
			    - AddNextDecorator.java -> Add the NEXT button on the base header ;
			    - AddPreviousDecorator.java -> Add the PREVIOUS button on the base header ;
			    - AddMarkingDecorator -> Add the MARKING button on the base header ;

	Package Factory : - FrameFactory.java -> abstract class that has object of the JFrame and method DisplayFrame() which call the abstract method FrameFactory()  
				and it's principal goal is to take the frame and refactor it based on the real object ;
			  - HomePageFactory.java -> takes the frame and refactor it to create the home page containing levels ;
			  - LessonPageFactory.java -> takes ArrayList of the lessons based on what level the user chooses and present them in the frame + saves the previous
				panel in the stack ; 	  
			  - ExerciseFactory.java -> class that saves previous panel in stack and create the new panel based on the user click ; 
			  - RuleFactory.java -> saves previous panel in the stack and creates the user interface of the rule based on the lesson selected ;

	Package Momento : - Momento.java -> saves the previous state of the frame in the stack variable to restore the previous panel on the button BACK click ;

	Package Observer : - DataSetChangedObserver.java -> has an arrayList of JComponent and notify the the component when any data changes in the exercise or lesson ;
			   - ExerciseObserver.java -> notify the lesson when all the exercise of this lesson are finished ;

	Package Proxy : - ProxyInterface.java -> contains method showPage() ;
			- StartUpControllerProxy.java -> check the email and password entered by the user , if entered info are true he will show the startUp page ;
			- EmailValidator.java -> contain a regular expression that check the validity of the email ;

	Package Controller : contains controllers for each view ;