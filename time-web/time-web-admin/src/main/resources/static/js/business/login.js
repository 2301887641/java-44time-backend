$(background)

//粒子背景特效
function background(){
    $('body').particleground({
        dotColor : 'rgba(255,255,255,0.6)',
        lineColor : 'rgba(200,200,200,0.4)',
        lineWidth:2.2,
        particleRadius:10,
        proximity: 120,
        density:16300,
        minSpeedX: 0.2,
        minSpeedY: 0.2,
        maxSpeedX: 0.2,
        maxSpeedY: 0.2
    });
}