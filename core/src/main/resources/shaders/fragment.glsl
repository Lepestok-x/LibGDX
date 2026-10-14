#version 330
out vec4 fragColor;
in vec4 v_color;
uniform float u_time;
void main() {
    vec4 color = v_color;
    color.r *= ((sin(u_time) * 0.35) + 0.5);
    fragColor = vec4(color.r, 0.0, 0.0, 1.0);
}
