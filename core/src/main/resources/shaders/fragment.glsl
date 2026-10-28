#version 330 core
out vec4 in_color;
in vec4 v_color;
in vec4 v_texcoord0;
uniform float u_projTime;
void main() {
    vec4 diffuse = v_color;
    vec4 coord = v_texcoord0;
    diffuse.r *= (sin(u_projTime) * 0.5) + 0.5;
    in_color = vec4(diffuse.rgb, v_color.a);
}
