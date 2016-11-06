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
uniform float m_Speed;

void main(){
    #ifdef HAS_COLORMAP
    	vec2 uv = inTexCoord;
    	uv.x += m_Speed * g_Time / 10;
        texCoord = uv;
    #endif

    #ifdef SEPARATE_TEXCOORD
        vec2 uv2 = inTexCoord2;
    	uv2.x += m_Speed * g_Time / 10;
        texCoord2 = uv2;
    #endif
    
    #ifdef HAS_VERTEXCOLOR
        vertColor = inColor;
    #endif

    vec4 modelSpacePos = vec4(inPosition, 1.0);

    gl_Position = TransformWorldViewProjection(modelSpacePos);
}