#import "Common/ShaderLib/GLSLCompat.glsllib"

#if defined(DISCARD_ALPHA)
    uniform float m_AlphaDiscardThreshold;
#endif

#if defined(HAS_TEX1)
	uniform sampler2D m_Tex1;
#endif

#if defined(HAS_TEX2)
	uniform sampler2D m_Tex2;
#endif

#if defined(HAS_TEX3)
	uniform sampler2D m_Tex3;
#endif

#if defined(HAS_TEX4)
	uniform sampler2D m_Tex4;
#endif

#if defined(HAS_TEX5)
	uniform sampler2D m_Tex5;
#endif

#if defined(HAS_TEX6)
	uniform sampler2D m_Tex6;
#endif

#if defined(HAS_TEX7)
	uniform sampler2D m_Tex7;
#endif

#if defined(HAS_TEX8)
	uniform sampler2D m_Tex8;
#endif

varying vec2 texCoord;

uniform float g_Time;

// shift time in secods;
uniform float m_ShiftSpeed;

void main(){
    vec4 color = vec4(1.0);
    
    int numTex = 1;
#if defined(HAS_TEX1)
	numTex = 1;
#endif

#if defined(HAS_TEX2)
	numTex = 2;
#endif

#if defined(HAS_TEX3)
	numTex = 3;
#endif

#if defined(HAS_TEX4)
	numTex = 4;
#endif

#if defined(HAS_TEX5)
	numTex = 5;
#endif

#if defined(HAS_TEX6)
	numTex = 6;
#endif

#if defined(HAS_TEX7)
	numTex = 7;
#endif

#if defined(HAS_TEX8)
	numTex = 8;
#endif
    
    int n = int(g_Time / m_ShiftSpeed);
    int s = n / numTex;
    n = n - s * numTex + 1;
	
    #ifdef HAS_TEX1
    	if (n == 1)
            color *= texture2D(m_Tex1, texCoord);     
    #endif
    
    #ifdef HAS_TEX2
    	if (n == 2)
            color *= texture2D(m_Tex2, texCoord);     
    #endif
    
    #ifdef HAS_TEX3
    	if (n == 3)
            color *= texture2D(m_Tex3, texCoord);     
    #endif
    
    #ifdef HAS_TEX4
    	if (n == 4)
            color *= texture2D(m_Tex4, texCoord);     
    #endif
    
    #ifdef HAS_TEX5
    	if (n == 5)
            color *= texture2D(m_Tex5, texCoord);     
    #endif
    
    #ifdef HAS_TEX6
    	if (n == 6)
            color *= texture2D(m_Tex6, texCoord);     
    #endif
    
    #ifdef HAS_TEX7
    	if (n == 7)
            color *= texture2D(m_Tex7, texCoord);     
    #endif
    
    #ifdef HAS_TEX8
    	if (n == 8)
            color *= texture2D(m_Tex8, texCoord);     
    #endif

    #if defined(DISCARD_ALPHA)
        if(color.a < m_AlphaDiscardThreshold){
           discard;
        }
    #endif
    
    gl_FragColor = color;
}