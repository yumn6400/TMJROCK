# JQuery_Library
Feature related to jquery are described.


                    JQUERY Features
With in <script> …</script>
1)	function great(){}
$(great);     ## great function will be invoked on document loaded.  ##

2)	 function doSomething ()
{
## those tag having cool as name set attribute ‘font-size as 24pt ‘ ##
$(“.cool”).css (“font-size”,”24pt”);

## those tag having span tag set attribute ‘border as 1px solid black ‘ ##
$(“span”).css (“border”:”1px solid black”);

## those tag having abc as id set attribute ‘border as 1px solid black ‘ ##
$(“#abc”).css (“background” : ”yellow”);
}
$(doSomething);

3)	$( ()=> {                 
$(“.cool”).css (“font-size”,”24pt”);
$(“span”).css (“border”:”1px solid black”);
$(“#abc”).css (“background” : ”yellow”);
});

4)	$( ()=>{
## tag having someSection as id add class cool to all division inside it. ##
$(“# someSection > div”).addClass(“cool”);
## tag having someSection as id not having class cool to division then add hot class to it. ##
$(“# someSection div: not(‘.cool’)”).add(Class(“hot”);
## tag having someSection as id and all division inside it contains cool class then add superclass to it. ##
$(“# someSection div.cool”).addClass(“superclass”);
## all tags having tmtm  as attribute  then apply css to it. ##
           $(“input[tmtm]”).css(“color”: “blue”);
           ## all tags having tmtm  as attribute  and its value as ok then apply css to     it. ##
           $(“input[tmtm=ok]”).css(“color”: “blue”);
           ## all tags having tmtm  as attribute  and contains first word as good in its value then apply css to it. ##
           $(“input[tmtm^=good]”).css(“color”: “blue”);
               ## all tags having tmtm  as attribute  and contains last word as good in its value then apply css to it. ##
           $(“input[tmtm$=good]”).css(“color”: “blue”);
           ## In li tag having 1st index then apply css to first index  (based on 0th index). ##
           $(“ li: eq(1) ”). css(“color”: “blue”);
           ## In li tag at every even index apply css to that index. ##
           $(“ li: even”). css(“color”: “blue”);
            ## In li tag at 3rd  index apply css to that index. ##
           $(“ li: nth-child(3)”). css(“color”: “blue”);
});

5)	 In Table
$( ()=>{
## all th tag of table tag attach class tbl to it. ##
$(“table th”).addClass(“tbl”);

## all th tag of table tag attach css to it. ##
$(“table th”).css(“color”,”blue”); 

## all th tag of table tag attach class css to it. ##
$(“table td:nth-child(2)”).css(“color”,”pink”);
## all td tag of tbody tag of table tag attach css to it. ##
$(“table tbody td: contains(‘Ujjain’)”). css(“color”,”pink”);

## all td tag of tbody tag of table tag at even index attach css to it. ##
$(“table tbody td”).filter(“:even”). css(“color”,”pink”);

## all td tag of tbody tag of table tag contains Ujjain attach css to it. ##
$(“table tbody td”:contains(‘Ujjain’)”). css(“color”,”pink”);

$(“table tbody td”:contains(‘Ujjain’)”).next(). css(“color”,”pink”);

$(“table tbody td”:contains(‘Ujjain’)”).nextAll(). css(“color”,”pink”);

$(“table tbody td”:contains(‘Ujjain’)”).nextAll().addBack(). css(“color”,”pink”);

$(“table tbody td”:contains(‘Ujjain’)”). parent().css(“color”,”pink”);

$(“table tbody td”:contains(‘Ujjain’)”). children().css(“color”,”pink”);

});

** If we create $ function then jquery call over function rather than its function firstly.
If we want to call there function then use jQuery at place of $ .
And use jQuery .noConflict();   so that they use jQuery at place of $.

If we want futher to use $ at place of jquery
Then use
jQuery( ($)=>{

});
inside it we can use $ . here $ act as local variable which represent object of JQuery.



## tag that contains button1 as id add css to it. ##
jQuery(“#button1”).css(“color”,”blue”);

##For use of click function both mean same 
     Tag with button2 as id call its click event . ##
1)	$(“#button2”).on(‘click’,function(){

});
2)	$(“#button2”).click(function(){

});
        
       ## Here this represent id as button3 ##     
       $(“#button3”).click(function(){
          $(this).css(‘color’,’red’);
          });
     
## In xyz div if anything is show then hide it and if hide then show it ##
           $(“#button4”).click(function(){
              $(“#abcd”.toggleClass(“xyz”);
          });

## Any part inside div tag is clicked then call this event ##
           $(“#button5”).click(function(ev){
              if(ev.target==this) alert(‘main container clicked’);
          });

## If any child class is inside a parent class and child class target event is called  on click inside child class if we want that after calling child class taget event not to call parent class target event then use this. ##
           $(“#button5”).click(function(ev){
              if(ev.target==this) alert(‘child class container clicked’);
               ev.stopPropagation();
          });

## If form is submitted and we want that if input in form value Is 0 then set it default ##
 $(“#button8”).click(function(ev){
    alert(‘Form is being submitted with value’+$(‘#nm’).val());
     If($(‘#nm’).val()==0)
    {
    ev.preventDefault(); 
     }
   });

## when  inside a d1 id tag if any tag having id s1 is clicked then print alert message ##
$(“#d1”).click(function(ev){
if($(ev.target).is(“#s1”))alert(‘s1 clicked’);
});

## To call another button click function from another  button id event ##
$(“#button9”).click(function(ev){
$(“#button10”).trigger(“click”);

});



                          Board Example
Types of key on Board
1)	Keydown: The event occur when a keyboard is pressed down.
2)	Keypress : The event occur is similar to keydown event. This event occur when a button is pressed down.
However,the keypress event is not fired for all keys(eg.
ALT, CTRL, SHIFT,ESC);
3)	Keyup : This event occur when a keyboard key is released.
Example of keydown on board :

$(()=>{
$(document).keydown(function(ev){
## A tag with board id print its acsii code ,key,code as : 72,h,keyH  ##
$(“#board”).append(ev.which+”,”+ev.key+”,”+ev.code+”<br>”);
## When press ctrl key then append info to div tag having board id . ##
If(ev.ctrlKey) $(“#board”).append(“CTRL key <br>”);
If(ev.altKey) $(“#board”).append(“ALT key <br>”);
If(ev.shiftKey) $(“#board”).append(“Shift key <br>”);
If(ev.metaKey) $(“#board”).append(“Meta key <br>”);
## If any effect due to press key then neglect it (not apply on meta key) 
Eg. F7 on click alert message pop up which is then neglected. ##
Ev.preventDefault();
});
});
## On click button with id as button1 then print message its tag having id as d1
And class as something  return its css property value and we can set value also. .##
$(()=>{
$(“#button1”).click(function(){
alert($(“#d1.something”).css(“font-size”));
alert($(“#d1.something”).css(“color”));
alert($(“#d1.something”).css(“font-family”));

alert($(“#d1.something”).css(“font-family”,”Verdana”));

});
});


var d2State=1;
$(()=>{
$(“#button2”).click(function(){
if(d2State)
{
## various effect can be apply on div tag content ##
$(“#d2”).hide();
$(“#d2”).hide(‘slow’);
$(“#d2”).fadeOut(‘slow’);
$(“#d2”).slideUp(“slow”);
}
else
{
## various effect can be apply on div tag content ##
$(“#d2”).show();
$(“#d2”).show(‘slow’);
$(“#d2”).fadeIn(‘slow’);
$(“#d2”).slideDown(“fast”);
}
D2State=!d2State;
});
});

OR
$(()=>{
//various effect that can apply on div tag contents

$(“#d2”).slideToggle(“slow”);
$(“#d2”).slideToggle(5000);
$(“#d2”).fadeToggle(“slow”);
});






  











