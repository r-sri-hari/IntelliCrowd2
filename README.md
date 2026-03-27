## Dataset Setup for ML Training

The project uses a video-based dataset pipeline for crowd analysis training.

### Folder structure

Place your raw crowd videos manually in:

- `dataset/raw_videos/train`
- `dataset/raw_videos/test`
- `dataset/raw_videos/validation`

Example:

- `dataset/raw_videos/train/crowd1.mp4`
- `dataset/raw_videos/test/crowd2.mp4`
- `dataset/raw_videos/validation/crowd3.mp4`

### Important

Raw videos, extracted frames, and generated patches are ignored in Git and are **not uploaded to GitHub**.

This is done to avoid pushing large files and generated dataset content.

### ML training pipeline

1. Place raw videos in the `raw_videos` folders
2. Run `VideoFrameExtractor.java` to extract frames
3. Run `PatchGenerator.java` to create 64x64 patches
4. Manually sort patches into:
    - `head`
    - `non_head`
5. Run `CNNTrainer.java` to train the DL4J model
6. The trained model will be used by `HeadPredictor.java`
7. `AHDSCalculator.java` uses those predictions to compute weighted crowd density

### Notes

- `train` = used for model learning
- `test` = used for evaluation
- `validation` = used for tuning and checking performance