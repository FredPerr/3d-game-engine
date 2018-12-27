#version 330 core

in vec2 passTextureCoordinates;
in vec3 colour;

out vec4 pixel;

uniform sampler2D textureSampler;
uniform bool usesTexture;

void main(void){
    if(usesTexture)
	    pixel = texture(textureSampler, passTextureCoordinates);
	else{
	    pixel = vec4(colour,1.0);
	}

}