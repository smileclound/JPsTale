#import "Common/ShaderLib/GLSLCompat.glsllib"
#import "Common/ShaderLib/Instancing.glsllib"

attribute vec3 inPosition;
attribute vec2 inTexCoord;
attribute vec2 inTexCoord2;
attribute vec4 inColor;

varying vec2 texCoord;
varying vec2 texCoord2;

varying vec4 vertColor;

// Scroll uv by time
uniform float g_Time;

void main(){
    float radius = 6.2831855 * g_Time * 0.125;
    float x = sin(radius) * 0.2;
    float y = cos(radius) * 0.2;
    #ifdef HAS_COLORMAP
    	texCoord = inTexCoord;
        texCoord.x += x;
        texCoord.y += y;
    #endif

    #ifdef SEPARATE_TEXCOORD
        texCoord2 = inTexCoord2;
        texCoord2.x += x;
        texCoord2.y += y;
    #endif
    
    #ifdef HAS_VERTEXCOLOR
        vertColor = inColor;
    #endif

    vec4 modelSpacePos = vec4(inPosition, 1.0);

    gl_Position = TransformWorldViewProjection(modelSpacePos);
}