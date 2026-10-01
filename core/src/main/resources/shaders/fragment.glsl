#ifdef GL_SL
precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform vec2 u_mouse;
uniform vec2 u_resolution;
void main() {
    vec4 color = texture2D(u_texture, v_texCoords);
    vec2 mouse = u_mouse.xy / u_resolution;
    vec2 uv = gl_FragCoord.xy / u_resolution;

    float distanceMouse = distance(uv, mouse);
    float gamma = pow(1.0 - distanceMouse, 5.0);
    gamma = clamp(gamma, 0.0, 1.0);

    gl_FragColor = vec4(color.rgb * gamma, color.a);
}
