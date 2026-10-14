#version 330
in vec3 a_position;
in vec4 a_color;
out vec4 v_color;
uniform mat4 u_projViewTrans;
uniform mat4 u_projViewWorldTrans;
uniform float u_time;

void main() {
    v_color = a_color;
    vec3 position = a_position;
    position.y += ((sin(u_time + 3.0 + position.y * 1.05) * 0.5) + 0.5);
    gl_Position = u_projViewTrans * u_projViewWorldTrans * vec4(position, 1.0);
}
