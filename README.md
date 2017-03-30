# MyApplication
相似图片查找
算法
缩小尺寸。
将图片缩小到8x8的尺寸，总共64个像素。这一步的作用是去除图片的细节，只保留结构、明暗等基本信息，摒弃不同尺寸、比例带来的图片差异。
简化色彩。
将缩小后的图片，转为64级灰度。也就是说，所有像素点总共只有64种颜色。
计算平均值。
计算所有64个像素的灰度平均值。
比较像素的灰度。
将每个像素的灰度，与平均值进行比较。大于或等于平均值，记为1；小于平均值，记为0。
计算哈希值。
将上一步的比较结果，组合在一起，就构成了一个64位的整数，这就是这张图片的指纹。组合的次序并不重要，只要保证所有图片都采用同样次序就行了。
对比指纹
看看64位中有多少位是不一样的。在理论上，这等同于计算”汉明距离”（Hamming distance）。如果不相同的数据位不超过5，就说明两张图片很相似；如果大于10，就说明这是两张不同的图片。
实现关键点
计算灰阶
private static double calculateGrayValue(int pixel) {
    int red = (pixel >> 16) & 0xFF;
    int green = (pixel >> 8) & 0xFF;
    int blue = (pixel) & 255;
    return 0.3 * red + 0.59 * green + 0.11 * blue;
}
汉明距离
最终指纹其实是 0101 的二进制数字，举例
111000
111111
那么这两个数字的汉明距离，其实就是 ^ 运算后 1 的个数。
private static int hamDist(long finger1, long finger2) {
    int dist = 0;
    long result = finger1 ^ finger2;
    while (result != 0) {
        ++dist;
        result &= result - 1;
    }
    return dist;
}
借鉴原文地址：http://gavinliu.cn/2017/03/20/Android-%E4%B8%80%E7%A7%8D%E7%9B%B8%E4%BC%BC%E5%9B%BE%E7%89%87%E6%90%9C%E7%B4%A2%E7%AE%97%E6%B3%95%E7%9A%84%E5%AE%9E%E7%8E%B0/
