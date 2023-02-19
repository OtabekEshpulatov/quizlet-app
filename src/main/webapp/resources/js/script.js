let biInput = document.querySelector('.clicking1');
biInput.addEventListener('change', ()=>{
    let filename = biInput.value.split('\\').splice(-1)
    document.querySelector('.clicking1out').innerHTML = filename;
})

let biInput2 = document.querySelector('.clicking2');
biInput2.addEventListener('change', ()=>{
    let filename = biInput2.value.split('\\').splice(-1)
    document.querySelector('.clicking2out').innerHTML = filename;
})