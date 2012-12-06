#pragma once

#include "model_desc.h"

namespace cg_homework
{
    class md2_model
    {
    public:
        md2_model();
        ~md2_model();

        bool load(istream &src);
        inline size_t num_frames() const;
        model_desc frame(size_t n) const;

    private:
        void cleanup();

    private:
        typedef model_desc::handle handle;
        vector<handle> frames_;
        handle texcoords_;
        handle indices_;

        size_t verts_size_;
        size_t texcoords_size_;
        size_t indices_size_;
    };

}

namespace md2
{

class model
{
public:
    struct vertex
    {
        float x, y, z;
    };
    struct texcoord
    {
        float u, v;
    };
    typedef vector<vertex> frame_t;

public:
    void load_md2(istream &src);

    const vector<frame_t>  &frames   () const {return frames_buffer   ;}
    const vector<texcoord> &texcoords() const {return texcoords_buffer;}
    const vector<int>      &indices  () const {return indices_buffer  ;}

private:    
    vector<frame_t> frames_buffer;
    vector<texcoord> texcoords_buffer;
    vector<int> indices_buffer;

};
    
    


}