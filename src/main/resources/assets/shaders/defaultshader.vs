#version 330 core

in vec3 location;
in vec2 textureCoordinates;

out vec3 colour;
out vec2 passTextureCoordinates;

uniform bool usesTexture;
uniform mat4 matrixTransformation;

void main(void){
	gl_Position = matrixTransformation *  vec4(location,1.0);
	if(usesTexture)
	    passTextureCoordinates = textureCoordinates;
	else
	    colour = vec3(location.x+0.5,0.0,location.y+0.5);
}