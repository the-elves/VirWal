// Initialize ClientTracker
// Important: If you replace the tracker file with your own, make sure to change the target name accordingly.
// Use a specific target name to match only a certain target or use a wildcard to match any or a certain group of targets.
// Create overlay for page one

this.tracker = new AR.ClientTracker("objs3.wtc");
var currentTrackObj
var overlayOne;
var shoppingListArray;
var currentItemIndex = 0;
var currentBuyName;
var nextToBuyName
var overlayTwo;
setShoppingListArray("blueCap;RedCap;aritel");
overlayOne = new AR.HtmlDrawable({
            html:"<div style=\"background-color:#C0C0C0\">Customer Reviews, Discount</br> Gamification Elements</div>"},
              1,{
              viewPortWidth: 100,
            updateRate: AR.HtmlDrawable.UPDATE_RATE.STATIC
          });

overlayTwo = new AR.HtmlDrawable({
            html:"<div style=\"background-color:#C0C0C0\">Click on Item to Proceed</div>"},
              1,{
              viewPortWidth: 100,
            updateRate: AR.HtmlDrawable.UPDATE_RATE.STATIC
          });

/*var onCurrentObjectDetected = function (){
    document.getElementById("topp").innerHTML = "current item detected";;
    currentItemIndex++;
    updateBuyPics();
    setCurrentTrackObj();

}*/

function setCurrentTrackObj(){
    currentTrackObj = new AR.Trackable2DObject(this.tracker, currentBuyName , {
    drawables: {
    cam: [overlayOne, overlayTwo]},
    onClick: function (object){
                            currentItemIndex++;
                            updateBuyPicNames();
                            updateBuyPics();
                            setCurrentTrackObj();

                            }
    });
}

function setShoppingListArray(s){
    shoppingListArray = s.split(";");
}

function updateBuyPicNames(){
    currentBuyName = shoppingListArray[currentItemIndex];
    nextToBuyName = shoppingListArray[currentItemIndex + 1];

}

function updateBuyPics(){
    document.getElementById("imgCurrentBuyPic").src = currentBuyName + ".jpg";
    document.getElementById("imgNextBuyPic").src = nextToBuyName + ".jpg";
}



updateBuyPicNames();
updateBuyPics();

setCurrentTrackObj();







/*var currentTrackObj
var overlayOne;

function onCurrentObjectDetected(){
    currentItemIndex++;
    updateBuyPics();
    setCurrentTrackObj();

}

function setCurrentTrackObj(){
    currentTrackObj = new AR.Trackable2DObject(this.tracker, currentBuyPic , {
    drawables: {
    cam: [overlayOne]},
    onEnterFieldOfVision : onCurrentObjectDetected
    });
}

this.tracker = new AR.ClientTracker("objs3.wtc");

var shoppingListArray;
var currentTrackObj = new AR.Trackable2DObject(this.tracker, currentBuyPic , {
drawables: {
cam: [overlayOne]
}
});

function setShoppingListArray(s){
    shoppingListArray = s.split(';');
}

function updateBuyPics(){
    document.getElementById("topp").src = currentBuyName + ".jpg";
    document.getElementById("topp").src = nextToBuyName + ".jpg";
}



var currentItemIndex = 0;

var currentBuyName = shoppingListArray[currentItemIndex];
var nextToBuyName = shoppingListArray[currentItemIndex + 1];

updateBuyPics();

overlayOne = new AR.HtmlDrawable({
            html:"<div style=\"background-color:#C0C0C0\">Customer Reviews</div>"},
              1,{
              viewPortWidth: 100,
            updateRate: AR.HtmlDrawable.UPDATE_RATE.STATIC
          });
*/