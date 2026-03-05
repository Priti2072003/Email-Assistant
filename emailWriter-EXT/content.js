
function findComposeToolbar(){
 const selectors =[
    '.btC',
    '.aDh',
    '[role ="toolbar"]',
    '.gU.Up'

];
for (const selector of selectors) {
   const toolbar = document.querySelector(selector);
    if(toolbar){
        return toolbar;
    }
    return null;
    
}
}



function getEmailContent(){
 const selectors =[
    'div[role="textbox"][g_editable="true"]',
    '.h7',
    '.a3s.aiL',
    '[role ="presentation"]'

];
for (const selector of selectors) {
     const content = document.querySelector(selector);
    if(content && content.innerText.trim()){
        return content.innerText.trim();
    }
    return '';
    
    
}
}

function createAIButton(){
 const button =document.createElement('button');
 button.className ='T-J J-J5-Ji aoO v7 T-I-atl L3';
 button.type ='button';
 button.style.marginRight ='8px';
 button.innerHTML ='AI Reply';
 return button;
}

function insertText(composeBox,text){
    composeBox.focus();
    composeBox.textContent = text;
    composeBox.dispatchEvent(new Event('input',{
        bubbles:true
    }));
}

function injectButton(){
const toolbar =findComposeToolbar();
if(!toolbar){
    console.log("Toolbar not found");
    return;
}
if(toolbar.querySelector('.ai-reply-button')) return;
const  button =createAIButton();

button.addEventListener('click',async () =>{
 try {
    button.innerText ='Generating...';
    button.disabled =true;

    const emailContent =getEmailContent();
    const response =await fetch('http://localhost:8080/api/email/generate',{
        method :'POST',
        headers :{
            'Content-Type':'application/json',
        },
        body :JSON.stringify({
            emailContent :emailContent,
            tone :'professional'
        })
    });
    if(!response.ok){
        throw new Error('API Request Failed');
    }

   const generatedReply= await response.text();
   const composeBox =document.querySelector('div[role ="textbox"][g_editable ="true"]');
   if(composeBox){
        insertText(composeBox,generatedReply);
    }
    else{
        console.error('ComposeBox was not found');
    }
 } catch (error) {
    console.error(error);
    alert('Failed to generate reply');
 }
 finally{
    button.innerText= 'AI Reply';
    button.disabled =false;
 }
});
toolbar.insertBefore(button,toolbar.firstChild);

}
const observer =new MutationObserver((mutations) =>{
  for (const mutation of mutations){
    for(const node of mutation.addedNodes){
    if(node.nodeType === 1 &&
        (node.matches('.aDh,.btC,[role="dialog"]') || node.querySelector?.('.aDh,.btC,[role="dialog"]'))){
          setTimeout(injectButton,500);
          return;
        }
    
    }
       
    }
  }
);

observer.observe(document.body,{
    childList :true,
    subtree :true
});


