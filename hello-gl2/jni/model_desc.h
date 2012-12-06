#pragma once

namespace cg_homework
{

struct model_desc
{
    typedef GLuint handle;

    handle verts;
    handle texcoords;
    handle indices;

    size_t verts_size;
    size_t texcoords_size;
    size_t indices_size;
};

}

