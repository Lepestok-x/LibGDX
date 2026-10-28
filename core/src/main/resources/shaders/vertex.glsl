#version 330
in vec4 a_color;
in vec4 a_texcoord0;
out vec4 v_color;
out vec4 v_texcoord0;
uniform mat4 u_projViewTrans;
uniform mat4 u_projViewWorldTrans;
in vec3 a_position;
void main() {
    v_color = a_color;
    v_texcoord0 = a_texcoord0;
    gl_Position = u_projViewTrans * u_projViewWorldTrans * vec4(a_position, 1.0);
}
