package trypResources;

public enum Operation
{
	ADD, //sin(x)+cos(x)
	SUBTRACT, //sin(x)-cos(x)
	MULTIPLY, //sin(x)*cos(x)
	DIVIDE, //sin(x)/cos(x)
	ENCLOSE, //sin(cos(x))
        POWER // sin(x)^cos(x)
}

//Add, Subtract, Multiply and divide are self explanatory.
//Enclose is for when you want to enclose the next function
//inside the current one, E.G sin(cos(x))
